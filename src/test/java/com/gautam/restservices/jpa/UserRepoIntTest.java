package com.gautam.restservices.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import com.gautam.restservices.entities.User;
import com.gautam.restservices.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoIntTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository repository;

  @Test
  public void whenFindByName_thenReturnUser() {
    // given
    User alex = new User();
    alex.setFirstName("PANKAJ");
    alex.setLastName("chaurasia");
    alex.setUserName("PANKAJ.chaurasia");
/*    entityManager.persist(alex);
    entityManager.flush();*/

    // when
    User found = repository.findByUserName(alex.getUserName());

    // then
    assertThat(found.getFirstName())
        .isEqualTo(alex.getFirstName());
  }
}
