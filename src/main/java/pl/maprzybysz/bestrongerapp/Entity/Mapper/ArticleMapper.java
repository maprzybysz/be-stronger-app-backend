package pl.maprzybysz.bestrongerapp.Entity.Mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import pl.maprzybysz.bestrongerapp.Entity.Article;
import pl.maprzybysz.bestrongerapp.Entity.DTO.ArticleDTO;




public class ArticleMapper {
    public static ModelMapper articleToArticleDTO(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Article, ArticleDTO>() {
            @Override
            protected void configure(){
               map().setId(source.getId());
               map().setTitle(source.getTitle());
               map().setContent(source.getContent());
               map().setCreateDate(source.getCreateDate());
               map().setAuthor(source.getAppUser().getUsername());
               map().setUrl(source.getImgUrl());
            }
        });
        return modelMapper;
    }
}




