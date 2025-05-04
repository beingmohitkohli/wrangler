@Test
public void testAggregateStats() throws Exception {
  String[] recipe = {
    "aggregate-stats :size_col :time_col total_size_mb total_time_sec"
  };

  List<Row> rows = Arrays.asList(
    new Row("size_col", "1MB").add("time_col", "2s"),
    new Row("size_col", "512KB").add("time_col", "500ms")
  );

  List<Row> result = TestingRig.execute(recipe, rows);

  Assert.assertEquals(1, result.size());
  Assert.assertEquals(1.5, result.get(0).getValue("total_size_mb"), 0.01);
  Assert.assertEquals(2.5, result.get(0).getValue("total_time_sec"), 0.01);
}
