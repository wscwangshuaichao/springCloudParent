package springcloud.learn.wsc.servicehi.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RedPacketUtil {

	// 最少金额
	private static final BigDecimal MINMONEY = new BigDecimal(1);

	// 最大金额
	private static final BigDecimal MAXMONEY = new BigDecimal(2000);

	// 随机值
	private static final BigDecimal TIMES = new BigDecimal("2.1");

	/**
	 * 计算每人获得红包金额;最小每人1元
	 * 
	 * @param amount
	 *            红包总额
	 * @param number
	 *            人数
	 * @return
	 */
	public static List<BigDecimal> randomHongBao(BigDecimal amount, int number) {
		if (amount.compareTo(new BigDecimal(2000)) == -1 || amount.compareTo(new BigDecimal(2000)) == 0) {
			if (amount.doubleValue() < number * 1) {
				return null;
			}
			Random random = new Random();
			// 金钱，按分计算 10块等于 1000分
			int money = amount.multiply(BigDecimal.valueOf(10)).intValue();
			// 随机数总额
			double count = 0;
			// 每人获得随机点数
			double[] arrRandom = new double[number];
			// 每人获得钱数
			List<BigDecimal> arrMoney = new ArrayList<BigDecimal>(number);
			// 循环人数 随机点
			for (int i = 0; i < arrRandom.length; i++) {
				int r = random.nextInt((number) * 9) + 1;
				count += r;
				arrRandom[i] = r;
			}
			// 计算每人拆红包获得金额
			int c = 0;
			for (int i = 0; i < arrRandom.length; i++) {
				// 每人获得随机数相加 计算每人占百分比
				Double x = new Double(arrRandom[i] / count);
				// 每人通过百分比获得金额
				int m = (int) Math.floor(x * money);
				// 如果获得 0 金额，则设置最小值 1分钱
				if (m == 0) {
					m = 1;
				}
				// 计算获得总额
				c += m;
				// 如果不是最后一个人则正常计算
				if (i < arrRandom.length - 1) {
					arrMoney.add(new BigDecimal(m).divide(new BigDecimal(10)));
				} else {
					// 如果是最后一个人，则把剩余的钱数给最后一个人
					arrMoney.add(new BigDecimal(money - c + m).divide(new BigDecimal(10)));
				}
			}
			// 随机打乱每人获得金额
			Collections.shuffle(arrMoney);
			return arrMoney;
		} else {
			while (true) {
				boolean flag = false;
				BigDecimal sum = new BigDecimal(0);
				List<BigDecimal> result = splitRedPackets(amount, number);
				for (int i = 0; i < result.size(); i++) {
					if (result.get(i).compareTo(new BigDecimal(0)) == 0
							|| result.get(i).compareTo(new BigDecimal(0)) == -1) {
						flag = true;
					}
					sum = sum.add(result.get(i));
				}
				if (sum.compareTo(amount) == 0 && !flag) {
					return result;
				}
			}
		}
	}

	private static boolean isRight(BigDecimal money, Integer count) {
		BigDecimal avg = money.divide(new BigDecimal(count), 1, BigDecimal.ROUND_UP);
		if (avg.compareTo(MINMONEY) == -1) {
			return false;
		} else if (avg.compareTo(MAXMONEY) == 1) {
			return false;
		}
		return true;
	}

	private static BigDecimal randomRedPacket(BigDecimal money, BigDecimal mins, BigDecimal maxs, Integer count) {
		if (count == 1) {
			return new BigDecimal((Math.round(money.multiply(new BigDecimal(100)).doubleValue())) / 100);
		}
		if (mins.compareTo(maxs) == 0) {
			return mins;// 如果最大值和最小值一样，就返回mins
		}
		BigDecimal max = null;
		if (maxs.compareTo(money) == 1) {
			max = new BigDecimal(money.toString());
		} else {
			max = new BigDecimal(maxs.toString());
		}
		BigDecimal one = max.subtract(mins).multiply(new BigDecimal(Math.random())).add(mins);
		one = new BigDecimal(Math.round(one.multiply(new BigDecimal(100)).doubleValue()));
		one = one.divide(new BigDecimal(100), 1, BigDecimal.ROUND_UP);
		BigDecimal moneyOther = money.subtract(one);
		if (isRight(moneyOther, count - 1)) {
			return one;
		} else {
			// 重新分配
			BigDecimal avg = moneyOther.divide(new BigDecimal(count).subtract(new BigDecimal(1)), 1,
					BigDecimal.ROUND_UP);
			if (avg.compareTo(MINMONEY) == -1) {
				return randomRedPacket(money, mins, one, count);
			} else if (avg.compareTo(MINMONEY) == 1) {
				return randomRedPacket(money, one, maxs, count);
			}
		}
		return one;
	}

	public static List<BigDecimal> splitRedPackets(BigDecimal money, Integer count) {
		if (!isRight(money, count)) {
			return null;
		}
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		BigDecimal max = money.multiply(TIMES).divide(new BigDecimal(count), 1, BigDecimal.ROUND_UP);
		if (max.compareTo(MAXMONEY) == 1) {
			max = new BigDecimal(MAXMONEY.toString());
		}
		for (int i = 0; i < count; i++) {
			BigDecimal one = randomRedPacket(money, MINMONEY, max, count - i);
			list.add(one);
			money = money.subtract(one);
		}
		Collections.shuffle(list);
		return list;
	}

	public static List<BigDecimal> fixedHongBao(BigDecimal amount, int number) {
		if (amount.doubleValue() < number * 1) {
			return null;
		}
		// 每人获得钱数
		List<BigDecimal> arrMoney = new ArrayList<BigDecimal>();
		for (int i = 0; i < number; i++) {
			arrMoney.add(amount.divide(new BigDecimal(number), 1, RoundingMode.HALF_DOWN));
		}
		return arrMoney;
	}

	public static void main(String[] args) {
		// RedPacketUtil red = new RedPacketUtil();
		// System.out.println(red.spiltRedPackets(150, 10));
		// List<BigDecimal> result = RedPacketUtil.randomHongBao(new BigDecimal(100),
		// 7);
		// BigDecimal total = new BigDecimal(0);
		// for (int i = 0; i < result.size(); i++) {
		// // System.out.println("result[" + i + "]:" + result[i]);
		// System.out.println(result.get(i));
		// total = total.add(result.get(i));
		// }
		// System.out.println(total);

		String sb = "a99/b223/cccc";
		List<String>list= Lists.newArrayList();
		System.out.println(get(sb,list));

//		System.out.println();

	}

	public static String get(String sb,List<String>reList) {
		int i = StringUtils.lastIndexOf(sb, "/");
		
		String result = null;
		if (i > 0) {
			result = StringUtils.remove(sb, StringUtils.substring(sb, i, sb.length()));
			reList.add(result);
			get(result,reList);
		}
		return reList.toString();
	}
}
