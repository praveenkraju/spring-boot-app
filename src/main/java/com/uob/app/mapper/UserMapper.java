package com.uob.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.uob.app.dto.AppUserDTO;
import com.uob.app.dto.UserAddressDTO;
import com.uob.app.dto.UserRespDTO;
import com.uob.app.entity.AppUser;
import com.uob.app.entity.UserAddress;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

	AppUserDTO userToUserDTO(AppUser user);
	
	AppUser userDTOToUser(AppUserDTO userDTO);

	@Mapping(target = "userPk", source = "appUser.userPk")
	UserAddressDTO userAddressToUserAddressDTO(UserAddress userAddress);
	
	@Mapping(target = "appUser.userPk", source = "userPk")
	UserAddress userAddressDTOToUserAddress(UserAddressDTO userAddressDTO);
	
	List<UserAddressDTO> userAddressesToUserAddressDTOs(List<UserAddress> userAddresses);
	List<UserAddress> userAddresseDTOsToUserAddresses(List<UserAddressDTO> userAddresseDTOs);
	
	UserRespDTO userToUserRespDTO(AppUser user);
}
