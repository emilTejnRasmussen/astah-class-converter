package entity;

import java.util.ArrayList;
import java.util.List;

public class ClassModel
{
    private final String className;
    private final List<FieldVariable> fieldVariables;
    private final List<Constructor> constructors;
    private final List<Method> methods;

    public ClassModel(String className, List<FieldVariable> fieldVariables, List<Constructor> constructors, List<Method> methods)
    {
        this.className = className;
        this.fieldVariables = fieldVariables;
        this.constructors = constructors;
        this.methods = methods;
    }

    public List<FieldVariable> getFieldVariables()
    {
        return new ArrayList<>(fieldVariables);
    }

    public List<Constructor> getConstructors()
    {
        return new ArrayList<>(constructors);
    }

    public List<Method> getMethods()
    {
        return new ArrayList<>(methods);
    }

    public String getClassName()
    {
        return className;
    }
}
