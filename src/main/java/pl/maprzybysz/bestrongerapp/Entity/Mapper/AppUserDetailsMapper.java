package pl.maprzybysz.bestrongerapp.Entity.Mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import pl.maprzybysz.bestrongerapp.Entity.AppUserDetails;
import pl.maprzybysz.bestrongerapp.Entity.DTO.AppUserDetailsDTO;


public class AppUserDetailsMapper {
    public static ModelMapper detailsToDetailsDTO(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<AppUserDetails, AppUserDetailsDTO>(){
            @Override
            protected void configure(){
               map().setWeight(source.getLastWeight());
               map().setHeight(source.getHeight());
            }
        });
        return modelMapper; }
}





