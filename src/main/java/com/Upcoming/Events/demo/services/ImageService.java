package com.Upcoming.Events.demo.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.Upcoming.Events.demo.models.Image;
import com.Upcoming.Events.demo.repositories.ImageRepository;

@Service
public class ImageService {
    
        private ImageRepository repository;
    
        public ImageService(ImageRepository repository){
            this.repository = repository;
        }
    
        public List<Image> getAll(){
            return repository.findAll();
        }
    
        public Image getOne(Long id) {
            Image Image = repository.findById(id).orElse(null);
            return Image;
        }
    
        public Image  save(Image Image) {
            Image ImageSaved = repository.save(Image);
            return ImageSaved;
        }
    
        public static Optional <Image> findById(Long id){
            return null;
        }
    
        public List<Image> delete(Long id){
            repository.deleteById(id);
            return repository.findAll();
        }
    }
