package uk.ac.glasgow.jagora;

/**
 * A thrown trade exception indicates a trade that could not be satisfied,
 * either because a trade was invalid for the associated buy or sell order; or
 * because one of the associated buying or selling traders could not satisfy the
 * trade within their available resources.
 * 
 * @author tws
 *
 */
public class TradeException extends Exception {

	/****/
	private static final long serialVersionUID = 1264454466445904865L;
	
	/**
	 * The trader responsible (if any) for the trade exception being thrown.
	 */
	public final Trader culprit;
	
	public TradeException(String message){
		super(message);
		this.culprit = null;
	}
	
	public TradeException(String message, Trader culprit){
		super(message);
		this.culprit = culprit;
	}

}
