package ai.runapi.openaitts.types;

import ai.runapi.core.types.RunApiValue;

abstract class OpenaittsValue extends RunApiValue {
  OpenaittsValue(String value) {
    super(value);
  }
}
