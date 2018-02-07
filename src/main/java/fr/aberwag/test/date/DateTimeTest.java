package fr.aberwag.test.date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.zone.ZoneRules;

import org.junit.Test;

import fr.aberwag.test.Loggable;

public class DateTimeTest implements Loggable {
	@Test
	public void instant() {
		// ---- Instant.EPOCH
		assertEquals("1970-01-01T00:00:00Z", Instant.EPOCH.toString());
		assertEquals(Instant.parse("1970-01-01T00:00:00Z"), Instant.EPOCH);
		assertEquals(Instant.ofEpochSecond(0), Instant.EPOCH);

		// ---- Instant.MIN
		assertEquals(Instant.parse("-1000000000-01-01T00:00:00Z"), Instant.MIN);

		// ---- Instant.MAX
		assertEquals(Instant.parse("+1000000000-12-31T23:59:59.999999999Z"), Instant.MAX);

		// ---- Few instance methods
		final Instant instant = Instant.now();

		// prints the current time
		// e.g. 2013-05-26T21:37Z (Coordinated Universal Time)
		logger().info("Instant.now() : {}", instant);
		// print the number of nano seconds
		logger().info("Instant.now().getNano() : {}", instant.getNano());
		// -- Working with 2 instants
		// 2013-05-26T23:10:40Z & 1530-05-26T23:10:40Z
		final Instant instant20130526_231040 = Instant.parse("2013-05-26T23:10:40Z");
		final Instant instant15300526_231040 = Instant.parse("1530-05-26T23:10:40Z");

		// 2013-05-26T23:10:40Z is After 1530-05-26T23:10:40Z
		assertTrue(instant20130526_231040.isAfter(instant15300526_231040));

		// 2013-05-26T23:10:40Z is NOT Before 1530-05-26T23:10:40Z
		assertFalse(instant20130526_231040.isBefore(instant15300526_231040));

		// 2013-05-26T23:10:40Z minus 1 hour (3600s)
		assertEquals(Instant.parse("2013-05-26T22:10:40Z"), instant20130526_231040.minusSeconds(3600));
		logger().info("----------------------------------------------------------------------------------------------");
	}

	@Test
	public void duration() {
		// ---- Duration.ZERO
		assertEquals(Duration.parse("PT0S"), Duration.ZERO);
		assertEquals("PT0S", Duration.ZERO.toString());

		// ---- 2h 5min 30s 345ms = 7_530_345ms
		final Duration duration = Duration.ofMillis(7_530_345);
		assertEquals("PT2H5M30.345S", duration.toString());
		assertEquals(7530, duration.getSeconds());
		assertEquals(345000000, duration.getNano());
	}

	@Test
	public void constants() {
		// ---- LocalDate
		// LocalDate.MIN & LocalDate.MAX
		assertEquals("-999999999-01-01", LocalDate.MIN.toString());
		assertEquals("+999999999-12-31", LocalDate.MAX.toString());

		// ---- LocalTime
		// LocalTime.MIN & LocalTime.MAX
		assertEquals("00:00", LocalTime.MIN.toString());
		assertEquals("23:59:59.999999999", LocalTime.MAX.toString());

		// LocalTime.NOON & LocalTime.MIDNIGHT
		// There is no mention of AM and PM
		assertEquals("12:00", LocalTime.NOON.toString());
		assertEquals("00:00", LocalTime.MIDNIGHT.toString());

		// ---- LocalDateTime
		// LocalDateTime.MIN & LocalDateTime.MAX
		assertEquals("-999999999-01-01T00:00", LocalDateTime.MIN.toString());
		assertEquals("+999999999-12-31T23:59:59.999999999", LocalDateTime.MAX.toString());
	}

	@Test
	public void now() {
		// ---- LocalDate
		// Current System Date
		// e.g. 2013-05-26
		logger().info("LocalDate.now(): {}", LocalDate.now());
		// Current UTC System Date
		// e.g. 2013-05-26
		logger().info("LocalDate.now(Clock.systemUTC()): {}", LocalDate.now(Clock.systemUTC()));
		// ---- LocalTime
		// Current System Time
		// e.g. 21:35:45.977
		logger().info("LocalTime.now(): {}", LocalTime.now());
		// Current UTC System Time
		// e.g. 19:35:45.977
		logger().info("LocalTime.now(Clock.systemUTC()): {}", LocalTime.now(Clock.systemUTC()));
		// ---- LocalDateTime
		// Current System Date and Time
		// e.g. 2013-05-26T21:35:45.977
		logger().info("LocalDateTime.now(): {}", LocalDateTime.now());
		// Current UTC System Date and Time
		// e.g. 2013-05-26T19:35:45.977
		logger().info("LocalDateTime.now(Clock.systemUTC()): {}", LocalDateTime.now(Clock.systemUTC()));
		logger().info("----------------------------------------------------------------------------------------------");
	}

	@Test
	public void localDateStaticMethods() {
		// LocalDate from a string
		final LocalDate localDateStr = LocalDate.parse("2013-05-23");
		assertEquals("2013-05-23", localDateStr.toString());

		// LocalDate from 3 integers (year, month, day)
		final LocalDate localDate = LocalDate.of(2013, 05, 26);
		assertEquals("2013-05-26", localDate.toString());

		// LocalDate with an offset from epoch
		final LocalDate oneHundredDaysBeforeEpoch = LocalDate.ofEpochDay(-1000);
		assertEquals("1967-04-07", oneHundredDaysBeforeEpoch.toString());

		// Copy of a LocalDate
		final LocalDate copyLocalDate = LocalDate.from(localDate);
		assertEquals("2013-05-26", copyLocalDate.toString());
	}

	@Test
	public void localTimeStaticMethods() {
		// LocalTime from a string
		final LocalTime timeStr1 = LocalTime.parse("12:35");
		assertEquals("12:35", timeStr1.toString());

		final LocalTime timeStr2 = LocalTime.parse("12:35:32.978");
		assertEquals("12:35:32.978", timeStr2.toString());

		// LocalDate from 3 integers (hour, minute, second)
		// But can be 2 (hour, minute)
		// or 4 (hour, minute, second, nanoseconds)
		final LocalTime timeAsInts = LocalTime.of(10, 22, 17);
		assertEquals("10:22:17", timeAsInts.toString());

		// LocalTime from a number of seconds after midnight
		final LocalTime oneHourAfterMidnight = LocalTime.ofSecondOfDay(3600);
		assertEquals("01:00", oneHourAfterMidnight.toString());

		// Copy of a LocalTime
		final LocalTime copyLocalTime = LocalTime.from(timeAsInts);
		assertEquals("10:22:17", copyLocalTime.toString());
	}

	@Test
	public void localDateTimeStaticMethods() {
		// LocalDateTime from a string
		final LocalDateTime localDateTimeStr = LocalDateTime.parse("2013-05-26T10:22:17");
		assertEquals("2013-05-26T10:22:17", localDateTimeStr.toString());

		// LocalDate from 5 parameters (year, month, day, hour, minute, second)
		// But range from 5 to 7.
		// Note : Month is an enum.
		final LocalDateTime localDateTime = LocalDateTime.of(2013, Month.MAY, 26, 12, 05);
		assertEquals("2013-05-26T12:05", localDateTime.toString());

		// LocalDateTime from a LocalDate and a LocalTime
		final LocalDate localDate = LocalDate.of(2013, 05, 26);
		final LocalTime localTime = LocalTime.of(12, 35);
		final LocalDateTime localDateTimeOfDateAndTime = LocalDateTime.of(localDate, localTime);
		assertEquals("2013-05-26T12:35", localDateTimeOfDateAndTime.toString());

		// Copy of a LocalDateTime
		final LocalDateTime copyLocalDateTime = LocalDateTime.from(localDateTime);
		assertEquals("2013-05-26T12:05", copyLocalDateTime.toString());
	}

	@Test
	public void localDateInstanceMethods() {
		final LocalDate localDate = LocalDate.of(2013, 05, 26);

		// Year
		assertEquals(2013, localDate.getYear());

		// Month
		assertEquals(Month.MAY, localDate.getMonth());
		assertEquals(5, localDate.getMonthValue());

		// Day
		assertEquals(26, localDate.getDayOfMonth());
		assertEquals(DayOfWeek.SUNDAY, localDate.getDayOfWeek());
		assertEquals(146, localDate.getDayOfYear());

		// Leap Year
		assertFalse(localDate.isLeapYear());
		assertTrue(LocalDate.of(2004, 05, 26).isLeapYear());

		// ---- Operations
		final LocalDate localDate2 = LocalDate.of(2013, 04, 26);

		// Before, After, Equal, equals
		assertTrue(localDate.isAfter(localDate2));
		assertFalse(localDate.isBefore(localDate2));
		assertTrue(localDate.isEqual(LocalDate.of(2013, 05, 26)));
		assertTrue(localDate.equals(LocalDate.of(2013, 05, 26)));

		// plus & minus
		assertEquals("2013-04-26", localDate.minusMonths(1).toString());
		assertEquals("2013-06-05", localDate.plusDays(10).toString());

		// Adjusters
		assertEquals("2013-05-01", localDate.with(TemporalAdjusters.firstDayOfMonth()).toString());
		assertEquals("2013-05-10", localDate.withDayOfMonth(10).toString());
	}

	@Test
	public void localTimeInstanceMethods() {
		final LocalTime localTime = LocalTime.of(12, 35, 25, 452_367_943);

		// Hour, Minute, Second, Nanosecond
		assertEquals(12, localTime.getHour());
		assertEquals(35, localTime.getMinute());
		assertEquals(25, localTime.getSecond());
		assertEquals(452_367_943, localTime.getNano());

		// ---- Operations
		// Before, After, Equal, equals
		final LocalTime localTime2 = LocalTime.of(12, 35, 25, 452_367_942);
		assertTrue(localTime.isAfter(localTime2));
		assertFalse(localTime.isBefore(localTime2));
		assertTrue(localTime.equals(LocalTime.of(12, 35, 25, 452_367_943)));

		/// plus & minus
		assertEquals("12:25:25.452367943", localTime.minusMinutes(10).toString());
		assertEquals("17:35:25.452367943", localTime.plusHours(5).toString());

		// Adjusters
		assertEquals("05:35:25.452367943", localTime.withHour(5).toString());
	}

	@Test
	public void localDateTimeInstanceMethods() {
		final LocalDate localDate = LocalDate.of(2013, 05, 26);
		final LocalTime localTime = LocalTime.of(12, 35, 25, 452_367_943);
		final LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

		// As LocalDate & LocalTime
		assertEquals("2013-05-26", localDateTime.toLocalDate().toString());
		assertEquals("12:35:25.452367943", localDateTime.toLocalTime().toString());
	}

	@Test
	public void zoneId() {
		final ZoneId zoneId = ZoneId.systemDefault();
		final ZoneRules zoneRules = zoneId.getRules();
		assertEquals("Europe/Paris", zoneId.toString());
		assertEquals("ZoneRules[currentStandardOffset=+01:00]", zoneRules.toString());

		// DST in effect
		assertTrue(zoneRules.isDaylightSavings(Instant.parse("2013-05-26T23:10:40Z")));
		assertFalse(zoneRules.isDaylightSavings(Instant.parse("2013-01-26T23:10:40Z")));
	}

	@Test
	public void zoneOffset() {
		final ZoneOffset zoneOffset = ZoneOffset.of("+06:00");
		assertEquals("+06:00", zoneOffset.toString());
		assertEquals(21600, zoneOffset.getTotalSeconds());

	}

	@Test
	public void zonedDateTime() {
		final LocalDateTime localDateTime = LocalDateTime.parse("2013-05-26T10:22:17");
		final ZoneId zoneId = ZoneId.of("Europe/Paris");
		final ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		assertEquals("2013-05-26T10:22:17+02:00[Europe/Paris]", zonedDateTime.toString());

		final ZoneOffset zoneOffset = ZoneOffset.from(zonedDateTime);
		assertEquals("+02:00", zoneOffset.toString());
	}

	@Test
	public void offsetDateTime() {
		final LocalDateTime localDateTime = LocalDateTime.parse("2013-05-26T10:22:17");
		final ZoneOffset zoneOffset = ZoneOffset.of("+02:00");
		final OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, zoneOffset);
		assertEquals("2013-05-26T10:22:17+02:00", offsetDateTime.toString());
	}

}
