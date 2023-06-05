//package com.example.blogservice.integrationtests;
//
//import com.example.blogservice.PostgresDatabaseContainerInitializer;
//import com.example.blogservice.entity.Category;
//import com.example.blogservice.entity.Comment;
//import com.example.blogservice.entity.Recipe;
//import com.example.blogservice.repository.PostRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ContextConfiguration(initializers = {PostgresDatabaseContainerInitializer.class})
//public class PostRepositoryIntegrationTests {
//    @Autowired
//    private PostRepository postRepository;
//
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @BeforeEach
//    void setUp() {
//        postRepository.deleteAll();
//        Category category=new Category();
//        category.setId(null);
//        category.setName("test");
//        category.setDescription("test_desc");
//        category.setPosts(new ArrayList<Recipe>());
//        entityManager.persist(category);
//        Recipe post=new Recipe(null,"test","test_descp","test_content",new HashSet<Comment>(),category);
//        entityManager.persist(post);
//    }
//
//    @Test
//    @Transactional
//    void findByCategoryIdTests() {
//        List<Recipe> lists=postRepository.findAll();
//        assertThat(lists.get(0).getCategory().getDescription()).isEqualTo("test_desc");
//    }
//}
