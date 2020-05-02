package shuttlegui.system;

import javax.swing.JTree;

public class TreeSystemNode extends ChildTreeNode<JTree, TreeSystemNode> {
	@Override
	JTree createBuiltComponent() {
		JTree result = new JTree();
		return result;
	}

	@Override
	TreeSystemNode getSelf() {
		return this;
	}
}
