package org.hbhk.aili.client.core.widget.wizard.action;

import org.hbhk.aili.client.core.widget.wizard.service.IJWizardComponents;
/**
* <b style="font-family:微软雅黑"><small>Description:向导组件取消Action，抽象类供继承</small></b>   </br>
 */
public abstract class CancelAction implements Action {

  private IJWizardComponents wizardComponents;

  public CancelAction(IJWizardComponents wizardComponents) {
    this.wizardComponents = wizardComponents;
  }

}