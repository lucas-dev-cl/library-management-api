package com.project4.Mapper;

import com.project4.DTO.BookDTO;
import com.project4.DTO.UserDTO;
import com.project4.Entity.BookEntity;
import com.project4.Entity.UserEntity;

public class MapperC {

	public static UserEntity toUserEntity(UserDTO userDto) {		
		return new UserEntity(userDto.getUsername(), userDto.getCountry());
	}
	
	public static UserDTO toDto(UserEntity userEntity) {
		return new UserDTO(userEntity.getUsername(), userEntity.getCountry());
	}
	
	public static BookEntity toBookEntity(BookDTO bookDto) {
		return new BookEntity(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getStock());
	}
	
}
