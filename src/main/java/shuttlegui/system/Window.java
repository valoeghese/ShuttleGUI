package shuttlegui.system;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window implements TreeNode<JFrame, Window> {
	Window() {
		// NO-OP
	}

	private int width = 300;
	private int height = 300;
	private List<TreeNode<?,?>> children = new ArrayList<>();

	public <U extends JComponent, N extends BranchingTreeNode<U, N>> Window child(N node) {
		this.children.add(node);
		return this;
	}

	public <U extends JComponent, N extends ComponentTreeNode<U, N>> Window child(N node) {
		this.children.add(node);
		return this;
	}

	public Window size(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}

	@Override
	public JFrame build() {
		JFrame top = new JFrame();
		JPanel next = new JPanel();
		BranchingTreeNode.applyWidgets(next::add, this.children);
		top.add(next);
		top.setSize(this.width, this.height);
		return top;
	}
}
