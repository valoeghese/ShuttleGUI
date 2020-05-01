package shuttlegui.system;

interface TreeNode<T, S extends TreeNode<T, S>> {
	T build();
}
