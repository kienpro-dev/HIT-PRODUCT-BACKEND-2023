package com.example.projectbase.service.impl;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.SuccessMessage;
import com.example.projectbase.domain.dto.AddressDto;
import com.example.projectbase.domain.dto.CustomerDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Address;
import com.example.projectbase.domain.entity.Customer;
import com.example.projectbase.domain.mapper.AddressMapper;
import com.example.projectbase.domain.mapper.CustomerMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.AddressRepository;
import com.example.projectbase.repository.CustomerRepository;
import com.example.projectbase.service.AddressService;
import com.example.projectbase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final CustomerRepository customerRepository;

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    private String getLocationName(AddressDto addressDto) {
        String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + addressDto.getLatitude() + "&lon=" + addressDto.getLongitude() + "&zoom=18&addressdetails=1";

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String json = response.toString();
                int startIndex = json.indexOf("\"display_name\":\"") + 16;
                int endIndex = json.indexOf("\"", startIndex);
                return json.substring(startIndex, endIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private double calculateDistance(AddressDto address1Dto, AddressDto address2Dto) {
        double earthRadius = 6371;

        double latDiff = Math.toRadians(address2Dto.getLatitude() - address1Dto.getLatitude());
        double lonDiff = Math.toRadians(address2Dto.getLongitude() - address1Dto.getLongitude());

        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                + Math.cos(Math.toRadians(address1Dto.getLatitude())) * Math.cos(Math.toRadians(address2Dto.getLatitude()))
                * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    @Override
    public AddressDto saveLocationCustomer(int customerId, AddressDto addressDto) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException(ErrorMessage.Customer.ERR_NOT_FOUND_ID, new String[]{String.valueOf(customerId)})));

        String addressName = getLocationName(addressDto);
        addressDto.setAddressName(addressName);
        Address address = addressMapper.toAddress(addressDto);

        boolean existByAddress = addressRepository.existsByLatitudeAndLongitude(addressDto.getLatitude(), addressDto.getLongitude());

        if(!existByAddress) {
            addressRepository.save(address);
        }
        customerRepository.saveLocation(addressRepository.findByLatitudeAndLongitude(addressDto.getLatitude(), address.getLongitude()).getId(), customerId);

        return addressDto;
    }
}
