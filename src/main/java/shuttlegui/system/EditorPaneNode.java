package shuttlegui.system;

import javax.swing.JEditorPane;

public class EditorPaneNode extends ChildTreeNode<JEditorPane, EditorPaneNode> {
	private boolean editable = false;

	public EditorPaneNode setEditable(boolean editable) {
		this.editable = editable;
		return this;
	}

	@Override
	JEditorPane createBuiltComponent() {
		JEditorPane pane = new JEditorPane();
		pane.setEditable(this.editable);
		return pane;
	}

	@Override
	EditorPaneNode getSelf() {
		return this;
	}
}
