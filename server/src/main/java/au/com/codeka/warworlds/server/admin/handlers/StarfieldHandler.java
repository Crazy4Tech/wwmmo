package au.com.codeka.warworlds.server.admin.handlers;

import au.com.codeka.warworlds.server.admin.RequestException;

/**
 * This handler is for /admin/debug/starfield, and allows us to explore the starfield itself.
 */
public class StarfieldHandler extends AdminHandler {
  @Override
  public void get() throws RequestException {
    render("starfield/index.html", null);
  }
}
