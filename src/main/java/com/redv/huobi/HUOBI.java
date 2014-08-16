package com.redv.huobi;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import si.mazi.rescu.ParamsDigest;

import com.redv.huobi.dto.account.HUOBIAccountInfo;
import com.redv.huobi.dto.marketdata.HUOBIDepth;
import com.redv.huobi.dto.marketdata.HUOBIOrderBookTAS;
import com.redv.huobi.dto.marketdata.HUOBITicker;
import com.redv.huobi.dto.trade.HUOBICancelOrderResult;
import com.redv.huobi.dto.trade.HUOBIOrder;
import com.redv.huobi.dto.trade.HUOBIPlaceOrderResult;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface HUOBI {

	@GET
	@Path("ticker_{symbol}_json.js")
	public HUOBITicker getTicker(@PathParam("symbol") String symbol)
			throws IOException;

	@GET
	@Path("depth_{symbol}_json.js")
	public HUOBIDepth getDepth(@PathParam("symbol") String symbol)
			throws IOException;

	@GET
	@Path("{symbol}_kline_{period}_json.js")
	public String[][] getKline(
			@PathParam("symbol") String symbol,
			@PathParam("period") String period) throws IOException;

	@GET
	@Path("detail_{symbol}_json.js")
	public HUOBIOrderBookTAS getDetail(@PathParam("symbol") String symbol)
			throws IOException;

	/**
	 * Fetch account information.
	 *
	 * @param method get_account_info
	 * @param accessKey the access key.
	 * @param created the time stamp of submitting the request.
	 * @param sign the MD5 signature.
	 * @return the account information.
	 */
	@POST
	@Path("apiv2.php")
	public HUOBIAccountInfo getAccountInfo(
		@FormParam("method") String method,
		@FormParam("access_key") String accessKey,
		@FormParam("created") long created,
		@FormParam("sign") ParamsDigest sign)
				throws IOException;

	/**
	 * Fetch all proceeding orders.
	 *
	 * @param method get_orders
	 * @param accessKey the access key.
	 * @param created the time stamp of submitting this request.
	 * @param sign the MD5 signature.
	 * @return the open orders.
	 */
	@POST
	@Path("apiv2.php")
	public HUOBIOrder[] getOrders(
		@FormParam("method") String method,
		@FormParam("access_key") String accessKey,
		@FormParam("coin_type") int coinType,
		@FormParam("created") long created,
		@FormParam("sign") ParamsDigest sign)
				throws IOException;

	/**
	 * Fetch order detail.
	 *
	 * @param method order_info
	 * @param accessKey the access key.
	 * @param id the order ID.
	 * @param created the time stamp of submitting this request.
	 * @param sign the MD5 signature.
	 * @return order detail.
	 */
	@POST
	@Path("apiv2.php")
	public HUOBIOrder getOrder(
		@FormParam("method") String method,
		@FormParam("access_key") String accessKey,
		@FormParam("coin_type") int coinType,
		@FormParam("id") long id,
		@FormParam("created") long created,
		@FormParam("sign") ParamsDigest sign)
				throws IOException;

	/**
	 * Place limit order.
	 *
	 * @param method buy/sell.
	 * @param accessKey the access key.
	 * @param price the price of the order.
	 * @param amount the quantity of the order.
	 * @param created the time stamp of submitting this request.
	 * @param sign the MD5 signature.
	 * @return the result of placing this order, contains order ID.
	 */
	@POST
	@Path("apiv2.php")
	public HUOBIPlaceOrderResult placeLimitOrder(
		@FormParam("method") String method,
		@FormParam("access_key") String accessKey,
		@FormParam("coin_type") int coinType,
		@FormParam("price") String price,
		@FormParam("amount") String amount,
		@FormParam("created") long created,
		@FormParam("sign") ParamsDigest sign)
				throws IOException;

	/**
	 * Place limit order.
	 *
	 * @param method buy/sell.
	 * @param accessKey
	 * @param price
	 * @param amount
	 * @param created
	 * @param sign
	 * @param tradePassword
	 * @return
	 */
	@POST
	@Path("apiv2.php")
	public HUOBIPlaceOrderResult placeLimitOrder(
			@FormParam("method") String method,
			@FormParam("access_key") String accessKey,
			@FormParam("coin_type") int coinType,
			@FormParam("price") String price,
			@FormParam("amount") String amount,
			@FormParam("created") long created,
			@FormParam("sign") ParamsDigest sign,
			@FormParam("trade_password") String tradePassword)
					throws IOException;

	/**
	 * Place market order.
	 *
	 * @param method buy_market/sell_market
	 * @param accessKey the access key.
	 * @param coinType the coin type, 1 means BTC, 2 means LTC.
	 * @param amount the quantity of the order.
	 * @param created the time stamp of submitting this request.
	 * @param sign the MD5 signature.
	 */
	@POST
	@Path("apiv2.php")
	public HUOBIPlaceOrderResult placeMarketOrder(
		@FormParam("method") String method,
		@FormParam("access_key") String accessKey,
		@FormParam("coin_type") int coinType,
		@FormParam("amount") String amount,
		@FormParam("created") long created,
		@FormParam("sign") ParamsDigest sign)
				throws IOException;

	/**
	 * Place market order.
	 *
	 * @param method buy_market/sell_market
	 * @param accessKey the access key.
	 * @param coinType the coin type, 1 means BTC, 2 means LTC.
	 * @param amount the quantity of the order.
	 * @param created the time stamp of submitting this request.
	 * @param sign the MD5 signature.
	 * @param tradePassword the trade password.
	 */
	@POST
	@Path("apiv2.php")
	public HUOBIPlaceOrderResult placeMarketOrder(
			@FormParam("method") String method,
			@FormParam("access_key") String accessKey,
			@FormParam("coin_type") int coinType,
			@FormParam("amount") String amount,
			@FormParam("created") long created,
			@FormParam("sign") ParamsDigest sign,
			@FormParam("trade_password") String tradePassword)
					throws IOException;

	/**
	 * Cancel order.
	 *
	 * @param method cancel_order
	 * @param accessKey the access key.
	 * @param id the ID of the order to be cancelled.
	 * @param created the time stamp of submitting the request.
	 * @param sign the MD5 signature.
	 */
	@POST
	@Path("apiv2.php")
	public HUOBICancelOrderResult cancelOrder(
		@FormParam("method") String method,
		@FormParam("access_key") String accessKey,
		@FormParam("coin_type") int coinType,
		@FormParam("id") long id,
		@FormParam("created") long created,
		@FormParam("sign") ParamsDigest sign)
				throws IOException;

	/**
	 * Modify order.
	 *
	 * <p>The old order will be cancelled, and a new order will be placed.</p>
	 *
	 * @param method modify_order
	 * @param accessKey the access key.
	 * @param id the order ID to be cancelled.
	 * @param price the price of the new order.
	 * @param amount the quantity of the new order.
	 * @param created the time stamp of submitting this request.
	 * @param sign the MD5 signature.
	 * @return the result of modifying order, contains the new order ID.
	 */
	@POST
	@Path("apiv2.php")
	public HUOBIPlaceOrderResult modifyOrder(
		@FormParam("method") String method,
		@FormParam("access_key") String accessKey,
		@FormParam("coin_type") int coinType,
		@FormParam("id") long id,
		@FormParam("price") String price,
		@FormParam("amount") String amount,
		@FormParam("created") long created,
		@FormParam("sign") ParamsDigest sign)
				throws IOException;

}
