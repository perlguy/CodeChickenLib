package codechicken.lib.asm;

import org.objectweb.asm.ClassWriter;

public class CC_ClassWriter extends ClassWriter {

    private final boolean runtime;

    public CC_ClassWriter(int flags) {
        this(flags, false);
    }

    public CC_ClassWriter(int flags, boolean runtime) {
        super(flags);
        this.runtime = runtime;
    }

    @Override
    protected String getCommonSuperClass(String type1, String type2) {
        String c = type1.replace('/', '.');
        String d = type2.replace('/', '.');
        if (ClassHierarchyManager.classExtends(d, c)) {
            return type1;
        }
        if (ClassHierarchyManager.classExtends(c, d)) {
            return type2;
        }
        do
            c = ClassHierarchyManager.getSuperClass(c, runtime); while (!ClassHierarchyManager.classExtends(d, c));

        return c.replace('.', '/');
    }
}
