import java.time.Instant;
import java.util.Random;

/**
 * Tester
 */
public class Tester {

  public static void main(String[] args) throws InterruptedException {

    int nativeRatio = 4;
    int mqttRatio = 12;

    var nativeThreshold = nativeRatio;
    var mqttThreshold = mqttRatio + nativeRatio;

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

    System.out.println("Native sum: "+nativeSum+". Mqtt sum: "+mqttSum);

  }
}