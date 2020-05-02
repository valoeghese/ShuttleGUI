package shuttlegui.system;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

	@Override
	public JFrame build() {
		JFrame top = new JFrame();
		JPanel next = new JPanel();
		BranchingTreeNode.applyWidgets(next::add, this.children);
		top.add(next);
		top.setSize(this.width, this.height);
		top.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0); // don't even ask why I have to add this. blame java
			}
		});
		return top;
	}
}
