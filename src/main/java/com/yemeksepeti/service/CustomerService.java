package com.yemeksepeti.service;

import com.yemeksepeti.dto.request.ActivateRequestDto;
import com.yemeksepeti.dto.request.CustomerLoginRequestDto;
import com.yemeksepeti.dto.request.CustomerRegisterRequestDto;
import com.yemeksepeti.dto.response.CustomerListResponseDto;
import com.yemeksepeti.dto.response.CustomerRegisterResponseDto;
import com.yemeksepeti.exceptions.ErrorType;
import com.yemeksepeti.exceptions.YemekSepetiException;
import com.yemeksepeti.mapper.ICustomerMapper;
import com.yemeksepeti.repository.ICustomerRepository;
import com.yemeksepeti.repository.entity.Customer;
import com.yemeksepeti.repository.enums.EStatus;
import com.yemeksepeti.utility.CodeGenerator;
import com.yemeksepeti.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService extends ServiceManager<Customer,Long>{
    private final ICustomerRepository repository;
    public CustomerService(ICustomerRepository repository){
        super(repository);
        this.repository=repository;
    }

    public CustomerRegisterResponseDto register(CustomerRegisterRequestDto dto){
        Customer customer = ICustomerMapper.INSTANCE.fromCustomerRegisterRequestDtoToCustomer(dto);
        customer.setActivationCode(CodeGenerator.generateCode());
        try {
            save(customer);
        }catch (Exception ex){
            throw new YemekSepetiException(ErrorType.INTERNAL_ERROR);
        }
        CustomerRegisterResponseDto responseDto = ICustomerMapper.INSTANCE.fromCustomertoCustomerRegisterResponseDto(customer);
        return responseDto;
    }
    public Boolean login(CustomerLoginRequestDto dto){
        Optional<Customer> optionalCustomer = repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(optionalCustomer.isEmpty()){
            throw new YemekSepetiException(ErrorType.USER_NOT_FOUND);
        }if(!optionalCustomer.get().getStatus().equals(EStatus.ACTIVE)){
            throw new YemekSepetiException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }return true;
    }
    public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<Customer> optionalCustomer = findById(dto.getId());
        if (optionalCustomer.isEmpty()){
            throw new YemekSepetiException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalCustomer.get().getStatus().equals(EStatus.ACTIVE)){
            throw new YemekSepetiException(ErrorType.ALREADY_ACTIVE);
        }
        if(dto.getActivationCode().equals(optionalCustomer.get().getActivationCode())){
            optionalCustomer.get().setStatus(EStatus.ACTIVE);
            update(optionalCustomer.get());
            return true;
        }else {
            throw new YemekSepetiException(ErrorType.INVALID_CODE);
        }
    }
    public List<CustomerListResponseDto> customerList() {
        return repository.findAll().stream().map(x->{
            return CustomerListResponseDto.builder().name(x.getName()).surname(x.getSurname()).build();
        }).collect(Collectors.toList());
    }
    public Optional<Customer> findOptionalByEmailAndCardDetails(String email,String cardDetails){
        return repository.findOptionalByEmailAndCardDetails(email,cardDetails);
    }


}
