package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		String pathSts = "E:\\Programas para Programação\\workSpaceEclipseTestes\\Enumerações e Composições\\arquivosParaExercicios\\vendas.csv";
		System.out.println("Entre com o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();

			List<Sale> sales = new ArrayList<>();

			while (line != null) {
				String[] full = line.split(",");
				int month = Integer.parseInt(full[0]);
				int year = Integer.parseInt(full[1]);
				String seller = full[2];
				int items = Integer.parseInt(full[3]);
				double total = Double.parseDouble(full[4]);

				sales.add(new Sale(month, year, seller, items, total));

				line = br.readLine();
			}

			System.out.println();
			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");
			List<Sale> sellers = sales.stream().filter(x -> x.getYear() == 2016)
					.sorted(Comparator.comparingDouble(x -> ((Sale) x).averagePrice()).reversed()).limit(5)
					.collect(Collectors.toList());

			sellers.forEach(System.out::println);

			System.out.println();

			double sum = sales.stream().filter(x -> x.getSeller().equals("Logan"))
					.filter(x -> x.getMonth() == 1 || x.getMonth() == 7).mapToDouble(x -> x.getTotal()).sum();
			System.out
					.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			sc.close();
		}

	}

}
