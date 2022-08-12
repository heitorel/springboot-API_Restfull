package com.workshopmongo.config;

import com.workshopmongo.domanin.Post;
import com.workshopmongo.domanin.User;
import com.workshopmongo.dto.AutorDTO;
import com.workshopmongo.dto.CommentDTO;
import com.workshopmongo.repository.PostRepository;
import com.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

public class Instantiation implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"),
                "Partiu viagem","Vou viajar para São Paulo. Abraços!", new AutorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"),
                "Bom dia","Acordei feliz hoje!", new AutorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano",
                sdf.parse("21/03/2018"), new AutorDTO(alex));

        CommentDTO c2 = new CommentDTO("Aproveite!",
                sdf.parse("23/03/2018"), new AutorDTO(bob));

        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",
                sdf.parse("23/03/2018"), new AutorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);

    }
}
