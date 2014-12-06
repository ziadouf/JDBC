package dbms;

	import java.util.ArrayList;

	import org.w3c.dom.Element;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;


class ordering {


	
		public void sortStrigAsc(NodeList nodeList, String tagName1) {
			int j;
			boolean flag = true; // set flag to true to begin first pass
			int temp; // holding variable

			while (flag) {
				flag = false; // set flag to false awaiting a possible swap

				for (j = 0; j < nodeList.getLength() - 1; j++) {
					Node tempNode1 = nodeList.item(j);
					Node tempNode2 = nodeList.item(j + 1);
					Node tempNode3 = nodeList.item(j);

					Node swap;
					Element eElement1 = (Element) tempNode1;
					Element eElement2 = (Element) tempNode2;

					if (tempNode1.getNodeType() == Node.ELEMENT_NODE) {

						if (eElement1
								.getElementsByTagName(tagName1)
								.item(0)
								.getTextContent()
								.compareTo(
										eElement2.getElementsByTagName(tagName1)
												.item(0).getTextContent()) > 0
								&& !tempNode3.equals(null)) // change to > for
															// ascending sort
						{
							swap = tempNode1; // swap elements
							tempNode1 = tempNode2;
							tempNode2 = swap;
							nodeList.item(j).getParentNode()
									.replaceChild(tempNode1, tempNode2);
							nodeList.item(j).getParentNode().appendChild(tempNode3);
							flag = true; // shows a swap occurred
						}

					}
				}
			}
		}

		public void sortStrigDsc(NodeList nodeList, String tagName1) {
			int j;
			boolean flag = true; // set flag to true to begin first pass
			int temp; // holding variable

			while (flag) {
				flag = false; // set flag to false awaiting a possible swap

				for (j = 0; j < nodeList.getLength() - 1; j++) {
					Node tempNode1 = nodeList.item(j);
					Node tempNode2 = nodeList.item(j + 1);
					Node tempNode3 = nodeList.item(j);

					Node swap;
					Element eElement1 = (Element) tempNode1;
					Element eElement2 = (Element) tempNode2;

					if (tempNode1.getNodeType() == Node.ELEMENT_NODE) {

						if (eElement1
								.getElementsByTagName(tagName1)
								.item(0)
								.getTextContent()
								.compareTo(
										eElement2.getElementsByTagName(tagName1)
												.item(0).getTextContent()) < 0
								&& !tempNode3.equals(null)) // change to > for
															// ascending sort
						{
							swap = tempNode1; // swap elements
							tempNode1 = tempNode2;
							tempNode2 = swap;
							nodeList.item(j).getParentNode()
									.replaceChild(tempNode1, tempNode2);
							nodeList.item(j).getParentNode().appendChild(tempNode3);
							flag = true; // shows a swap occurred
						}

					}
				}
			}
		}

		public void sortIntAsc(NodeList nodeList, String tagName1) {
			int j;
			boolean flag = true; // set flag to true to begin first pass
			int temp; // holding variable

			while (flag) {
				flag = false; // set flag to false awaiting a possible swap

				for (j = 0; j < nodeList.getLength() - 1; j++) {
					Node tempNode1 = nodeList.item(j);
					Node tempNode2 = nodeList.item(j + 1);
					Node tempNode3 = nodeList.item(j);

					Node swap;
					Element eElement1 = (Element) tempNode1;
					Element eElement2 = (Element) tempNode2;

					if (tempNode1.getNodeType() == Node.ELEMENT_NODE) {

						if (Integer.parseInt(eElement1
								.getElementsByTagName(tagName1).item(0)
								.getTextContent()) > Integer.parseInt(eElement2
								.getElementsByTagName(tagName1).item(0)
								.getTextContent())
								&& !tempNode3.equals(null)) // change to > for
															// ascending sort
						{
							swap = tempNode1; // swap elements
							tempNode1 = tempNode2;
							tempNode2 = swap;
							nodeList.item(j).getParentNode()
									.replaceChild(tempNode1, tempNode2);
							nodeList.item(j).getParentNode().appendChild(tempNode3);
							flag = true; // shows a swap occurred
						}

					}
				}
			}
		}

		public void sortIntDsc(NodeList nodeList, String tagName1) {
			int j;
			boolean flag = true; // set flag to true to begin first pass
			int temp; // holding variable

			while (flag) {
				flag = false; // set flag to false awaiting a possible swap

				for (j = 0; j < nodeList.getLength() - 1; j++) {
					Node tempNode1 = nodeList.item(j);
					Node tempNode2 = nodeList.item(j + 1);
					Node tempNode3 = nodeList.item(j);

					Node swap;
					Element eElement1 = (Element) tempNode1;
					Element eElement2 = (Element) tempNode2;

					if (tempNode1.getNodeType() == Node.ELEMENT_NODE) {

						if (Integer.parseInt(eElement1
								.getElementsByTagName(tagName1).item(0)
								.getTextContent()) < Integer.parseInt(eElement2
								.getElementsByTagName(tagName1).item(0)
								.getTextContent())
								&& !tempNode3.equals(null)) // change to > for
															// ascending sort
						{
							swap = tempNode1; // swap elements
							tempNode1 = tempNode2;
							tempNode2 = swap;
							nodeList.item(j).getParentNode()
									.replaceChild(tempNode1, tempNode2);
							nodeList.item(j).getParentNode().appendChild(tempNode3);
							flag = true; // shows a swap occurred
						}

					}
				}
			}
		}

	}


