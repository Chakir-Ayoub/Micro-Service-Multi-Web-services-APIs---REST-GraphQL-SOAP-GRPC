package com.example.demo.entities.web;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.stub.CustomerServiceGrpc.CustomerServiceImplBase;
import com.example.demo.stub.CustomerServiceOuterClass;
import com.example.demo.stub.CustomerServiceOuterClass.GetAllCustomersRequest;
import com.example.demo.stub.CustomerServiceOuterClass.GetCustomerByIdRequest;
import com.example.demo.stub.CustomerServiceOuterClass.GetCustomerByIdResponse;
import com.example.demo.stub.CustomerServiceOuterClass.GetCustomersResponse;
import com.example.demo.stub.CustomerServiceOuterClass.SaveCustomerRequest;
import com.example.demo.stub.CustomerServiceOuterClass.SaveCustomerResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CustomerGrpcService extends CustomerServiceImplBase{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerMapper customerMapper;
	@Override
	public void getAllCustomers(GetAllCustomersRequest request, StreamObserver<GetCustomersResponse> responseObserver) {
		// TODO Auto-generated method stub
		
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustomers =
                customerList.stream()
                        .map(customerMapper::fromCustomer).collect(Collectors.toList());

        CustomerServiceOuterClass.GetCustomersResponse customersResponse=
                CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                        .addAllCustomers(grpcCustomers)
                        .build();
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();

	}
	
	@Override
	public void getCustomerById(GetCustomerByIdRequest request,
			StreamObserver<GetCustomerByIdResponse> responseObserver) {
		// TODO Auto-generated method stub
		Customer customer=customerRepository.findById(request.getCustomerId()).get();
		
		CustomerServiceOuterClass.GetCustomerByIdResponse response=
		CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
				.setCustomer(customerMapper.fromCustomer(customer))
				.build();
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void saveCustomer(SaveCustomerRequest request, StreamObserver<SaveCustomerResponse> responseObserver) {
		// TODO Auto-generated method stub
        CustomerServiceOuterClass.CustomerRequest customerRequest = request.getCustomer();
        Customer customer=customerMapper.fromGrpcCustomerRequest(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse response=
                CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomer(savedCustomer))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
}
