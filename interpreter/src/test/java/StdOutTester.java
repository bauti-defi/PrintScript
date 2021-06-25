import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StdOutTester implements Consumer<String> {

  public final List<String> out = new ArrayList<>();

  @Override
  public void accept(String s) {
    out.add(s);
  }
}
