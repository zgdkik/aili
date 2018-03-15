package org.hbhk.aili.client.core.widget.wizard.action;

import org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents;

/**
* <b style="font-family:微软雅黑"><small>Description:向导组件完成Action，抽象类供继承</small></b>   </br>
 */
public abstract class FinishAction implements Action {

  private IJWizardComponents wizardComponents;

  public FinishAction(IJWizardComponents wizardComponents) {
    this.wizardComponents = wizardComponents;
  }

}