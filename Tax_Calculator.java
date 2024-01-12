import java.util.Scanner;

public class Tax_Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter taxable income (R): ");
        double taxableIncome = scanner.nextDouble();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        System.out.print("Enter year: ");
        int taxYear = scanner.nextInt();

        double tax = calculateIncomeTax(taxableIncome, age, taxYear);

        System.out.println("Income Tax for the year " + taxYear + ": R" + tax);
    }

    private static double calculateIncomeTax(double taxableIncome, int age, int taxYear) {
        double threshold = getTaxThreshold(age, taxYear);
        double rebate = getRebate(age, taxYear);
        double total_tax = 0;

        if (taxableIncome <= threshold) {
            return 0;
        }

        if (taxYear == 2022) {
            if (taxableIncome > 0 && taxableIncome <= 216_200) {
                total_tax = taxableIncome * 0.18;
            } else if (taxableIncome <= 337_800) {
                total_tax = 38_916 + 0.26 * (taxableIncome - 216_200);
            } else if (taxableIncome <= 467_500) {
                total_tax = 70_532 + 0.31 * (taxableIncome - 337_800);
            } else if (taxableIncome <= 613_600) {
                total_tax = 110_739 + 0.36 * (taxableIncome - 467_500);
            } else if (taxableIncome <= 782_200) {
                total_tax = 163_335 + 0.39 * (taxableIncome - 613_600);
            } else if (taxableIncome <= 1_656_600) {
                total_tax = 229_089 + 0.41 * (taxableIncome - 782_200);
            } else {
                total_tax = 587_593 + 0.45 * (taxableIncome - 1_656_600);
            }
        }else if (taxYear == 2023){
            if (taxableIncome > 0 && taxableIncome <= 226_000) {
                total_tax = taxableIncome * 0.18;
            } else if (taxableIncome <= 353_100) {
                total_tax = 40_680 + 0.26 * (taxableIncome - 226_000);
            } else if (taxableIncome <= 488_700) {
                total_tax = 73_726 + 0.31 * (taxableIncome - 353_100);
            } else if (taxableIncome <= 641_400) {
                total_tax = 115_762 + 0.36 * (taxableIncome - 488_700);
            } else if (taxableIncome <= 817_600) {
                total_tax = 170_734 + 0.39 * (taxableIncome - 641_400);
            } else if (taxableIncome <= 1_731_600) {
                total_tax = 239_452 + 0.41 * (taxableIncome - 817_600);
            } else {
                total_tax = 614_192 + 0.45 * (taxableIncome - 1_731_600);
            }
        }else if(taxYear == 2024){
            if (taxableIncome > 0 && taxableIncome <= 237_100) {
                total_tax = taxableIncome * 0.18;
            } else if (taxableIncome <= 370_500) {
                total_tax = 42_678 + 0.26 * (taxableIncome - 237_100);
            } else if (taxableIncome <= 512_800) {
                total_tax = 77_362 + 0.31 * (taxableIncome - 370_500);
            } else if (taxableIncome <= 673_000) {
                total_tax = 121_475 + 0.36 * (taxableIncome - 512_800);
            } else if (taxableIncome <= 857_900) {
                total_tax = 179_147 + 0.39 * (taxableIncome - 673_000);
            } else if (taxableIncome <= 1_817_000) {
                total_tax = 251_258 + 0.41 * (taxableIncome - 857_900);
            } else {
                total_tax = 644_489 + 0.45 * (taxableIncome - 1_817_000);
            }

        }

        // After getting the tax, we subtract the rebate
        return total_tax -= rebate;

        // Ensure the final tax is non-negative
        // return Math.max(0, total_tax);
    }



    private static double getRebate(int age, int taxYear) {
        if (taxYear == 2022) {
            if (age < 65) {
                return 15_714;
            } else if (age >= 65 && age < 75) {
                return 15_714 + 8_613;
            } else if (age >= 75) {
              return 15_714 + 8_613 + 2_871;
            }else{
                return 0;
            }
            
        } else if (taxYear == 2023) {
            if (age < 65) {
                return 16_425;
            } else if (age >= 65 && age < 75) {
                return 16_425 + 9_000;
            } else if (age >= 75) {
              return 16_425 + 9_000 + 2_997;
            }else{
                return 0;
            }
        } else if (taxYear == 2024) {
            if (age < 65) {
                return 17_235;
            } else if (age >= 65 && age < 75) {
                return 17_235 + 9_444;
            } else if (age >= 75) {
              return 17_235 + 9_444 + 3_145;
            }else{
                return 0;
            }
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }
    }
    

    private static double getTaxThreshold(int age, int taxYear) {
        // Provide the tax threshold based on age and tax year
        if (taxYear == 2022) {
            if (age > 0 && age < 65) {
                return 87_300;
            } else if (age >= 65 && age <= 75) {
                return 135_150;
            } else {
                return 151_100;
            }
        } else if (taxYear == 2023) {
           if (age > 0 && age < 65) {
                return 91_250;
            } else if (age >= 65 && age <= 75) {
                return 141_250;
            } else {
                return 157_900;
            }
        } else if (taxYear == 2024) {
           if (age > 0 && age < 65) {
                return 95_750;
            } else if (age >= 65 && age <= 75) {
                return 148_217;
            } else {
                return 165_689;
            }
        } else {
            throw new IllegalArgumentException("Invalid tax year");
        }

    }
}
