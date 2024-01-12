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
                total_tax = 587_593 + 0.44 * (taxableIncome - 1_656_600);
            }
        }else if (taxYear == 2023){

        }

        // After getting the tax, we subtract the rebate
        total_tax -= rebate;

        // Ensure the final tax is non-negative
        return Math.max(0, total_tax);
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
