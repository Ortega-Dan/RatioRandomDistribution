import java.time.Instant;
import java.util.Random;

/**
 * Tester
 */
public class Tester {

  public static void main(String[] args) throws Exception {

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