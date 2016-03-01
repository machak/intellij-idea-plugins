package luonq;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiManager;

/**
 * Created by Luonanqin on 11/13/14.
 */
public class ScrollFromSource extends AnAction {

	private static final Logger LOG = Logger.getInstance(ScrollFromSource.class);

	public void actionPerformed(AnActionEvent e) {

		final Project project = e.getProject();
		final VirtualFile file = CommonDataKeys.VIRTUAL_FILE.getData(e.getDataContext());

		if (project != null && file != null) {
			final VirtualFile target = file.getCanonicalFile();

			if (target != null) {
				final PsiManager psiManager = PsiManager.getInstance(project);
				final PsiFileSystemItem psiFile = target.isDirectory() ? psiManager.findDirectory(target) : psiManager.findFile(target);
				if (psiFile != null) {
					ProjectView.getInstance(project).select(psiFile, target, true);
				}
			}
		}
	}
}
