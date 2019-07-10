package reflection;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.Assert;

@RunWith(JUnit4.class)
public class ReflectionTest {
	
	private AbovePerson abovePerson = new AbovePerson();
	private BelowPerson belowPerson = new BelowPerson();
	
	@Before
	public void setup() {
		belowPerson.setAge(22);
		belowPerson.setName("TOME");
		belowPerson.setSecret("MINE MINE ALL MINE");
	}
	
	@Test
	public void doTest() {
			try {
				ReflectionUtil.convert(belowPerson, abovePerson);
				assertTrue(abovePerson.getAge().equals(belowPerson.getAge()));
				assertTrue(abovePerson.getName().equals(belowPerson.getName()));
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
	}

}
