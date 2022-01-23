package indiv.park.starter.inheritance;

import indiv.park.starter.exception.ModuleException;

public interface ModuleBase {

	public void initialize(Class<?> mainClass) throws ModuleException;

	public void setConfiguration(Object property);
}
