package shuttlegui.system;

import java.awt.Color;
import java.awt.Font;
import java.util.function.Consumer;

import javax.annotation.Nullable;
import javax.swing.JComponent;
import javax.swing.border.Border;

abstract class ChildTreeNode<T extends JComponent, S extends ChildTreeNode<T, S>> implements TreeNode<T, S> {
	@Nullable Color background;
	@Nullable Color foreground;
	@Nullable Border border;
	@Nullable Font font;
	@Nullable Integer width;
	@Nullable Integer height;
	@Nullable Consumer<T> onBuild;

	public S setBackground(Color colour) {
		this.background = colour;
		return this.getSelf();
	}

	public S setForeground(Color colour) {
		this.foreground = colour;
		return this.getSelf();
	}

	public S setBorder(Border border) {
		this.border = border;
		return this.getSelf();
	}

	public S setFont(Font font) {
		this.font = font;
		return this.getSelf();
	}

	public S setSize(int width, int height) {
		this.width = width;
		this.height = height;
		return this.getSelf();
	}

	public S onBuild(Consumer<T> r) {
		this.onBuild = r;
		return this.getSelf();
	}

	@Override
	public final T build() {
		T result = this.createBuiltComponent();
		this.onComponentBuild(result);

		if (this.onBuild != null) {
			this.onBuild.accept(result);
		}

		return result;
	}

	void onComponentBuild(T component) {
		this.addDecorations(component);
	}

	/**
	 * Called by the default implementation of {@link ChildTreeNode#onComponentBuild}
	 */
	void addDecorations(T component) {
		if (this.background != null) component.setBackground(this.background);
		if (this.foreground != null) component.setForeground(this.foreground);
		if (this.border != null) component.setBorder(this.border);
		if (this.font != null) component.setFont(this.font);
		if (this.width != null) component.setSize(this.width, this.height);
	}

	abstract T createBuiltComponent();
	abstract S getSelf();
}
