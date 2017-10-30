/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprosesing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Vector;

import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.RevisionHandler;

/**
 *
 * @author Momon
 */
public abstract class Tokenizer implements Enumeration<String>, OptionHandler,
  Serializable, RevisionHandler {

  /** Added to avoid warning */
  private static final long serialVersionUID = 7781271062738973996L;

  /**
   * Returns a string describing the stemmer
   * 
   * @return a description suitable for displaying in the explorer/experimenter
   *         gui
   */
  public abstract String globalInfo();

  /**
   * Returns an enumeration of all the available options..
   * 
   * @return an enumeration of all available options.
   */
  @Override
  public Enumeration<Option> listOptions() {
    return (new Vector<Option>()).elements();
  }

  /**
   * Gets the current option settings for the OptionHandler.
   * 
   * @return the list of current option settings as an array of strings
   */
  @Override
  public String[] getOptions() {
    return new String[0];
  }

  /**
   * Sets the OptionHandler's options using the given list. All options will be
   * set (or reset) during this call (i.e. incremental setting of options is not
   * possible).
   * 
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  @Override
  public void setOptions(String[] options) throws Exception {
    // nothing in this class
  }

  /**
   * Tests if this enumeration contains more elements.
   * 
   * @return true if and only if this enumeration object contains at least one
   *         more element to provide; false otherwise.
   */
  @Override
  public abstract boolean hasMoreElements();

  /**
   * Returns the next element of this enumeration if this enumeration object has
   * at least one more element to provide.
   * 
   * @return the next element of this enumeration.
   */
  @Override
  public abstract String nextElement();

  /**
   * Sets the string to tokenize. Tokenization happens immediately.
   * 
   * @param s the string to tokenize
   */
  public abstract void tokenize(String s);

  /**
   * initializes the given tokenizer with the given options and runs the
   * tokenizer over all the remaining strings in the options array. If no
   * strings remained in the option string then data is read from stdin, line by
   * line.
   * 
   * @param tokenizer the tokenizer to use
   * @param options the options for the tokenizer
   * @return the tokenized strings
   * @throws Exception if setting of options or tokenization fails
   */
  public static String[] tokenize(Tokenizer tokenizer, String[] options)
    throws Exception {
    Vector<String> result;
    Vector<String> tmpResult;
    Vector<String> data;
    int i;
    boolean processed;
    BufferedReader reader;
    String line;

    result = new Vector<String>();

    // init tokenizer
    tokenizer.setOptions(options);

    // for storing the data to process
    data = new Vector<String>();

    // run over all un-processed strings in the options array
    processed = false;
    for (i = 0; i < options.length; i++) {
      if (options[i].length() != 0) {
        processed = true;
        data.add(options[i]);
      }
    }

    // if no strings in option string then read from stdin
    if (!processed) {
      reader = new BufferedReader(new InputStreamReader(System.in));
      while ((line = reader.readLine()) != null) {
        data.add(line);
      }
    }

    // process data
    for (i = 0; i < data.size(); i++) {
      tmpResult = new Vector<String>();
      tokenizer.tokenize(data.get(i));
      while (tokenizer.hasMoreElements()) {
        tmpResult.add(tokenizer.nextElement());
      }
      // add to result
      result.addAll(tmpResult);
    }

    return result.toArray(new String[result.size()]);
  }

  /**
   * initializes the given tokenizer with the given options and runs the
   * tokenizer over all the remaining strings in the options array. The
   * generated tokens are then printed to stdout. If no strings remained in the
   * option string then data is read from stdin, line by line.
   * 
   * @param tokenizer the tokenizer to use
   * @param options the options for the tokenizer
   */
  public static void runTokenizer(Tokenizer tokenizer, String[] options) {
    String[] result;
    int i;

    try {
      result = tokenize(tokenizer, options);
      for (i = 0; i < result.length; i++) {
        System.out.println(result[i]);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}