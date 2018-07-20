import java.util.Random;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class dice {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Error!");
      System.exit(1);
    }

    Random rand = new Random();
    String val = args[0].toString();
    String regstr1 = "^[0-9]+";
    String regstr2 = "[0-9]+$";
    String regstr3 = "\\+[0-9]+$";
    Pattern reg1 = Pattern.compile(regstr1);
    Pattern reg2 = Pattern.compile(regstr2);
    Pattern reg3 = Pattern.compile(regstr3);
    Matcher mat1 = reg1.matcher(val);
    Matcher mat2 = reg2.matcher(val);
    Matcher mat3 = reg3.matcher(val);
    // rand_count D rand_int
    int rand_count = 0;
    int rand_int = 0;
    int i = 0;
    int[] rand_arr;
    int rand_result = 0;
    int add_int = 0;
    String result = "";
    Boolean add_int_flag = false;

    if(val.matches("(^[0-9]+[D|d]{1}[0-9]+$)|(^[0-9]+[D|d]{1}[0-9]+\\+[0-9]+$)") && mat1.find() && mat2.find()){
        if(mat3.find()){
            add_int_flag = true;
            rand_count = Integer.parseInt(mat1.group());
            rand_int = Integer.parseInt(mat2.group());
            add_int = Integer.parseInt(mat3.group());
        }else{
            rand_count = Integer.parseInt(mat1.group());
            rand_int = Integer.parseInt(mat2.group());
        }
    } else {
        System.out.println("Error!");
        System.exit(1);
    }

    rand_arr = new int[rand_count];
    while (rand_count > i) {
        int rand_i = (rand.nextInt(rand_int) + 1);
        rand_arr[i] = rand_i;
        rand_result = rand_result + rand_i;
        i++;
    }
    
    Arrays.sort(rand_arr);
    
    if (rand_count >= 2){
        for (int ii = 0; ii < rand_arr.length; ii++) {
            result = result + rand_arr[ii] + ",";
        }
        if(add_int_flag){
            result = result.replaceAll(",$", "");
            result = rand_result + "[" + result + "]+" + add_int + " -> ";
        } else {
            result = result.replaceAll(",$", "");
            result = rand_result + "[" + result + "] -> ";
        }
    }else{
        if(add_int_flag){
            result = rand_result + "+" + add_int + " -> ";
        }
    }

    int result_int = rand_result + add_int;

    System.out.println("(" + val + ")" + " -> " + result + result_int);
  }
}
