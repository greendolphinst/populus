/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.core;
import java.lang.reflect.*;

public final class ModelPacket implements java.io.Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -6919970167290967332L;
	private String modelName;
	private Class<? extends Model> modelClass;

	public Class<? extends Model> getModelClass() {
		return modelClass;
	}

	public String getModelName() {
		return modelName;
	}

	public ModelPacket( Class<? extends Model> modelClass ) {
		this.modelClass = modelClass;

		//Extract model name using class
		try {
			Method m = modelClass.getMethod( "getModelName", new Class[0] );
			modelName = (String)m.invoke( null, null );
		}
		catch( Exception e ) {
			this.modelName = "Error Finding Model Name";
			System.err.println( "Error finding model name for " + modelClass );
			System.err.println( e );
		}
	}
}
