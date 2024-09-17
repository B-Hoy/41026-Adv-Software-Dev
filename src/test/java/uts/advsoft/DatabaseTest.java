import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import uts.advsoft.Database;

class CalculatorTest{

	private final Database db = new Database();

	@Test
	void name(){
		assertTrue(db.is_admin_password("admin"), "Admin password is \"admin\"");
	}
}
