package com.odb.view.dashboard.client.dto;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.odb.view.dashboard.client.DataVO;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


/**
 * The Class LiveChartVO.
 */
@SuppressWarnings("serial")
public class LiveChartVO extends DataVO {

	/** The date. */
	private Date date;
	
	/** The variable. */
	private double variable;
	
	/** The variable2. */
	private double variable2;
	
	/** The variable3. */
	private double variable3;

	/**
	 * Instantiates a new live chart vo.
	 */
	private LiveChartVO() {
	}

	/**
	 * Instantiates a new live chart vo.
	 *
	 * @param date the date
	 * @param variable the variable
	 * @param variable2 the variable2
	 * @param variable3 the variable3
	 */
	public LiveChartVO(Date date, double variable, double variable2, double variable3) {
		this();
		this.date = date;
		this.variable = variable;
		this.variable2 = variable2;
		this.variable3 = variable3;
	}

	/**
	 * The Interface LiveCharPropertyAccess.
	 */
	public static interface LiveCharPropertyAccess extends
			PropertyAccess<LiveChartVO> {
		
		/**
		 * Date.
		 *
		 * @return the value provider
		 */
		ValueProvider<LiveChartVO, Date> date();

		/**
		 * Name key.
		 *
		 * @return the model key provider
		 */
		@Path("date")
		ModelKeyProvider<LiveChartVO> nameKey();

		/**
		 * Variable.
		 *
		 * @return the value provider
		 */
		ValueProvider<LiveChartVO, Double> variable();

		/**
		 * Variable2.
		 *
		 * @return the value provider
		 */
		ValueProvider<LiveChartVO, Double> variable2();

		/**
		 * Variable3.
		 *
		 * @return the value provider
		 */
		ValueProvider<LiveChartVO, Double> variable3();
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the variable.
	 *
	 * @return the variable
	 */
	public double getVariable() {
		return variable;
	}

	/**
	 * Sets the variable.
	 *
	 * @param variable the new variable
	 */
	public void setVariable(double variable) {
		this.variable = variable;
	}

	/**
	 * Gets the variable2.
	 *
	 * @return the variable2
	 */
	public double getVariable2() {
		return variable2;
	}

	/**
	 * Sets the variable2.
	 *
	 * @param variable2 the new variable2
	 */
	public void setVariable2(double variable2) {
		this.variable2 = variable2;
	}

	/**
	 * Gets the variable3.
	 *
	 * @return the variable3
	 */
	public double getVariable3() {
		return variable3;
	}

	/**
	 * Sets the variable3.
	 *
	 * @param variable3 the new variable3
	 */
	public void setVariable3(double variable3) {
		this.variable3 = variable3;
	}

}
