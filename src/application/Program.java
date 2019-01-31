package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Pessoa;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		double salary = sc.nextDouble();
		List<Pessoa> list = new ArrayList<>();

		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

			String line = bf.readLine();
			while (line != null) {
				String [] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				double salaryPerson = Double.parseDouble(fields[2]);
				list.add(new Pessoa(name, email, salaryPerson));
				line = bf.readLine();
			}
			System.out.println("Email of people whose salary is more than "+ String.format("%.2f",salary) + ":" );
			
			List<String> emailsPessoa = list.stream().filter(p -> p.getSalary()>salary).map(p ->p.getEmail()).sorted().collect(Collectors.toList());
			
			emailsPessoa.forEach(System.out::println);
			System.out.print("Enter a letter: ");
			sc.nextLine();
			char letter = sc.nextLine().charAt(0);
			Double sum = list.stream().filter(p -> p.getName().charAt(0) == letter).map(p -> p.getSalary()).reduce(0.0,(x,y)->x+y);
			System.out.printf("Sum of salary of people whose name starts with '"+letter+"': %.2f%n", sum );
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}

}
