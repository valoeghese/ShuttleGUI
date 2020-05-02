package shuttlegui.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window implements TreeNode<JFrame, Window> {
	Window() {
		// NO-OP
	}

	private int width = 300;
	private int height = 300;
	@Nullable private String title;
	private List<ChildTreeNode<?,?>> children = new ArrayList<>();

	public <U extends JComponent, N extends ChildTreeNode<U, N>> Window child(N node) {
		this.children.add(node);
		return this;
	}

	public Window size(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}

	public Window title(String title) {
		this.title = title;
		return this;
	}

	@Override
	public JFrame build() {
		JFrame top = new JFrame();
		JPanel next = new JPanel();
		BranchingTreeNode.applyWidgets(next::add, this.children);
		top.add(next);
		top.setSize(this.width, this.height);
		top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (this.title != null) {
			top.setTitle(this.title);
		}

		return top;
	}
}
