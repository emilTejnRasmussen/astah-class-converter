package entity;

import java.util.List;

public record Method(String visibility, String returnType, String name, List<Argument> arguments)
{
}
