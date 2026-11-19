package com.shatteredpixel.shatteredpixeldungeon.web.compiler;

import org.teavm.classlib.ReflectionContext;
import org.teavm.classlib.ReflectionSupplier;
import org.teavm.model.ClassReader;
import org.teavm.model.MethodDescriptor;
import org.teavm.model.ValueType;

import java.util.Collection;
import java.util.Collections;

/**
 * Build-time only (used by the TeaVM compiler, never shipped in the game).
 * Saves are restored with Class.forName(name).newInstance(), so every game class
 * must be findable by name and keep its no-argument constructor.
 * We grant only that: granting access to all fields/methods makes the TeaVM
 * dependency analysis run out of memory.
 */
public class SPDReflectionSupplier implements ReflectionSupplier {

	private static final MethodDescriptor NO_ARG_CONSTRUCTOR = new MethodDescriptor("<init>", ValueType.VOID);

	private static boolean isGameClass(String name) {
		return name.startsWith("com.shatteredpixel.shatteredpixeldungeon.")
				|| name.startsWith("com.watabou.");
	}

	@Override
	public boolean isClassFoundByName(ReflectionContext context, String name) {
		return isGameClass(name);
	}

	@Override
	public Collection<MethodDescriptor> getAccessibleMethods(ReflectionContext context, String className) {
		if (!isGameClass(className)) return Collections.emptyList();
		ClassReader cls = context.getClassSource().get(className);
		if (cls == null || cls.getMethod(NO_ARG_CONSTRUCTOR) == null) return Collections.emptyList();
		return Collections.singletonList(NO_ARG_CONSTRUCTOR);
	}
}
