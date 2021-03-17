package pl.maprzybysz.bestrongerapp.Entity.Mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import pl.maprzybysz.bestrongerapp.Entity.DTO.MealDTO;
import pl.maprzybysz.bestrongerapp.Entity.Meal;

public class MealMapper {
    public static ModelMapper mealToMealDTO(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Meal, MealDTO>() {
            @Override
            protected void configure(){
                map().setId(source.getId());
                map().setName(source.getName());
                map().setGrammage(source.getGrammage());
                map().setGoodness(source.getGoodness());
                map().setProtein(source.getProtein());
                map().setCarbohydrates(source.getCarbohydrates());
                map().setFat(source.getFat());
                map().setDescription(source.getMealDetails().getDescription());
                map().setImgUrl(source.getMealDetails().getImgUrl());
                map().setCreateDate(source.getMealDetails().getCreateDate());
                map().setAuthor(source.getAppUser().getUsername());
            }
        });
        return modelMapper;
    }

}
