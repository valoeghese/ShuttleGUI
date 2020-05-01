package shuttlegui.system;

import javax.swing.JMenu;

public class MenuNode extends BranchingTreeNode<JMenu, MenuNode> {
	@Override
	JMenu createBuiltComponent() {
		JMenu result = new JMenu();
		return result;
	}

	@Override
	MenuNode getSelf() {
		return this;
	}
}
