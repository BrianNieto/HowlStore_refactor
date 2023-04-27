package com.asj.bootcamp.mapper;

import com.asj.bootcamp.dto.ContactDTO;
import com.asj.bootcamp.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDTO contactEntityToContactDTO(Contact contact);

    Contact contactDTOToContactEntity(ContactDTO contactDTO);

}