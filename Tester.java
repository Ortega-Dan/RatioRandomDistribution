import java.time.Instant;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Tester
 */
public class Tester {

  public static void main(String[] args) {

    var logger = Logger.getLogger(Tester.class.getCanonicalName());

    String rangeStart = "AU7900508HYD00001";

    int prefixLength = 12;

    String prefix = rangeStart.substring(0, prefixLength);
    long startLongValue = Long.parseLong(rangeStart.substring(prefixLength), 16);

    for (int i = 0; i < 4000; i++) {

      var newHexValue = Long.toHexString(startLongValue + i).toUpperCase();

      String.format("%" + (rangeStart.length() - prefixLength) + "s", newHexValue).replace(' ', '0');

    }

  }

  public static void TESTER(String[] args) throws Exception {

    int nativeRatio = 3;
    int mqttRatio = 1;

    if (nativeRatio < 0 || mqttRatio < 0) {
      throw new Exception("Distribution ratios must be positive integers");
    }

    var nativeThreshold = nativeRatio;
    var mqttThreshold = mqttRatio + nativeRatio;

    if (mqttThreshold == 0) {
      System.out.println("Set distribution ratios to integer values greater than 0 for tests to run.");
      return;
    }

    var randr = new Random(Instant.now().getNano());

    var nativeSum = 0;
    var mqttSum = 0;

    for (int i = 0; i < 40; i++) {

      var result = randr.nextInt(mqttThreshold) + 1;

      System.out.println(result);
      if (result > nativeThreshold && result <= mqttThreshold) {
        System.out.println("Mqtt");
        mqttSum++;
      } else {
        nativeSum++;
        System.out.println("Native");
      }
    }

    System.out.println("Native sum: " + nativeSum + ". Mqtt sum: " + mqttSum);

  }
}