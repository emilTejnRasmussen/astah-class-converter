import entity.ClassModel;
import service.AstahTextGenerator;
import service.ClassParser;

import java.util.Scanner;

void main()
{
    Scanner scanner = new Scanner(System.in);
    StringBuilder inputBuilder = new StringBuilder();

    System.out.println("Paste your Java class below.");
    System.out.println("When you are done, type END on a new line and press Enter:");
    System.out.println();

    while (scanner.hasNextLine())
    {
        String line = scanner.nextLine();

        if (line.equals("END"))
        {
            break;
        }

        inputBuilder.append(line).append(System.lineSeparator());
    }

    String javaClass = inputBuilder.toString();

    ClassParser parser = new ClassParser();
    ClassModel classModel = parser.stringToClassModel(javaClass);

    AstahTextGenerator generator = new AstahTextGenerator();

    System.out.println();
    System.out.println("Class name:");
    System.out.println(classModel.getClassName());

    System.out.println();
    System.out.println("Fields for Astah:");
    System.out.println(generator.classModelToAstahFields(classModel));

    System.out.println("Operations for Astah:");
    System.out.println(generator.classModelToAstahMethods(classModel));
}