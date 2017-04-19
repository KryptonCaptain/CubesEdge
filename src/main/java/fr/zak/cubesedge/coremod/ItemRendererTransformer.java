package fr.zak.cubesedge.coremod;

import java.util.ArrayList;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ItemRendererTransformer implements IClassTransformer {

	private String renderItemMethodName;
	private static String className;

	@Override
	public byte[] transform(String name, String transformedName,
			byte[] basicClass) {
		if (transformedName
				.equals("net.minecraft.client.renderer.ItemRenderer")) {
			System.out
					.println("Cube\'s Edge Core - Patching class ItemRenderer...");
			renderItemMethodName = CubesEdgeLoadingPlugin.obfuscation ? "a"
					: "renderItemInFirstPerson";
			className = CubesEdgeLoadingPlugin.obfuscation ? "bly"
					: "net/minecraft/client/renderer/ItemRenderer";

			ClassReader cr = new ClassReader(basicClass);
			ClassNode cn = new ClassNode(Opcodes.ASM4);
			cr.accept(cn, 0);
			for (Object mnObj : cn.methods) {
				MethodNode mn = (MethodNode) mnObj;
				if (mn.name.equals(renderItemMethodName)
						&& mn.desc.equals("(F)V")) {
					patchRenderHandMethod(mn);
				}
			}
			ClassWriter cw = new ClassWriter(0); //orig code
			//ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES); //squeek code
			
			cn.accept(cw);
			System.out
					.println("Cube\'s Edge Core - Patching class ItemRenderer done.");
			return cw.toByteArray();
		} else {
			return basicClass;
		}
	}

	private static void patchRenderHandMethod(MethodNode mn) {

		System.out.println("\tPatching method renderItemFirstPerson in ItemRenderer");
		InsnList newList = new InsnList();

		mn.localVariables = new ArrayList<LocalVariableNode>(5);
		
		newList.add(new VarInsnNode(Opcodes.ALOAD, 0));
		newList.add(new VarInsnNode(Opcodes.FLOAD, 1));
		newList.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
				"fr/zak/cubesedge/coremod/Patch",
				"renderItemInFirstPerson", "(L" + className + ";F)V", false));
		newList.add(new InsnNode(Opcodes.RETURN));

		//mn.instructions = newList; //orig code
		mn.instructions.insertBefore(findFirstInstruction(mn), newList); //squeek recommended code
	}
	
	//squeek code
	private static AbstractInsnNode findFirstInstruction(MethodNode method)
	{
		for (AbstractInsnNode instruction = method.instructions.getFirst(); instruction != null; instruction = instruction.getNext())
		{
			if (instruction.getType() != AbstractInsnNode.LABEL && instruction.getType() != AbstractInsnNode.LINE)
				return instruction;
		}
		return null;
	}
	
}
