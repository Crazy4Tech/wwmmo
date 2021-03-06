package au.com.codeka.warworlds.client.game.world;

import android.support.annotation.Nullable;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;

import au.com.codeka.warworlds.client.App;
import au.com.codeka.warworlds.client.store.ProtobufStore;
import au.com.codeka.warworlds.common.proto.Empire;

/** Manages empires. */
public class EmpireManager {
  public static final EmpireManager i = new EmpireManager();

  private final ProtobufStore<Empire> empires;

  /** Our current empire, will be null before we're connected. */
  @Nullable private Empire myEmpire;

  private EmpireManager() {
    empires = App.i.getDataStore().empires();
  }

  /** Called by the server when we get the 'hello', and lets us know the empire. */
  public void onHello(Empire empire) {
    empires.put(empire.id, empire);
    myEmpire = empire;
    App.i.getEventBus().publish(empire);
  }

  /** Gets my empire, if my empire hasn't been set yet, IllegalStateException is thrown. */
  @Nonnull
  public Empire getMyEmpire() {
    Preconditions.checkState(myEmpire != null);
    return myEmpire;
  }

  public boolean isEnemy(@Nullable Empire empire) {
    if (empire == null) {
      return false;
    }
    if (empire.id == null) {
      return true;
    }
    if (myEmpire != null && !empire.id.equals(myEmpire.id)) {
      return true;
    }
    return false;
  }

  public Empire getEmpire(long id) {
    if (myEmpire != null && myEmpire.id.equals(id)) {
      return myEmpire;
    }
    // TODO
    return null;
  }
}

