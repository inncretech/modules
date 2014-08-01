package dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tastetablet.catalogue.db.beans.Category;
import com.tastetablet.catalogue.db.beans.CategoryRelationship;
import com.tastetablet.catalogue.db.repository.CategoryRelationshipRepository;
import com.tastetablet.catalogue.db.repository.CategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-profiles.xml", "classpath:/dao.xml" })
@ActiveProfiles(profiles = "dev")
public class CategoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryRelationshipRepository categoryRelationshipRepository;

	@Test
	public void addCategory() {
		Category category = new Category();
		category.setCategoryName("T-Shirt Clothing ");
		category.setDescription("T-Shirt Clothing  Category");
		category.setIsActive(true);
		category.setIsDeleted(false);
		categoryRepository.save(category);
		Category category1 = new Category();
		category1.setCategoryName("Shirt Clothing");
		category1.setDescription("Shirt Clothing Category");
		category1.setIsActive(true);
		category1.setIsDeleted(false);
		categoryRepository.save(category1);
	}

	//@Test
	public void addCategoryRelationShip() {
		CategoryRelationship categoryRelationship = new CategoryRelationship();
		Category category = new Category();
		category.setCategoryId(8);
		categoryRelationship.setCategory1(category);
		Category category2 = new Category();
		category2.setCategoryId(2);
		categoryRelationship.setCategory2(category2);
		categoryRelationship.setCreateDate(new Date());
		categoryRelationship.setIsActive(true);
		categoryRelationship.setIsDeleted(false);
		categoryRelationship.setModiDate(new Date());

		categoryRelationshipRepository.save(categoryRelationship);
	}
}
